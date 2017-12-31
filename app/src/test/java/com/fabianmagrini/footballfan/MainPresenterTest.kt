package com.fabianmagrini.footballfan

import com.fabianmagrini.footballfan.helpers.BaseMainView
import com.fabianmagrini.footballfan.helpers.BaseFootballFanRepository
import com.fabianmagrini.footballfan.helpers.Example
import com.fabianmagrini.footballfan.model.Post
import com.fabianmagrini.footballfan.presenter.MainPresenter
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@Suppress("IllegalIdentifier")
class MainPresenterTest {
    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler {
            Schedulers.trampoline()
        }
        RxJavaPlugins.setNewThreadSchedulerHandler {
            Schedulers.trampoline()
        }
    }

    @Test
    fun `After view was created, list of characters is loaded and displayed`() {
        assertOnAction { onViewCreated() }.thereIsSameListDisplayed()
    }

    @Test
    fun `New list is shown after view was refreshed`() {
        assertOnAction { onRefresh() }.thereIsSameListDisplayed()
    }

    @Test
    fun `When API returns error, it is displayed on view`() {
        // Given
        val someError = Error()
        var errorDisplayed: Throwable? = null
        val view = BaseMainView(
                onShow = { _ -> fail() },
                onShowError = { errorDisplayed = it }
        )
        val marvelRepository = BaseFootballFanRepository { Single.error(someError) }
        val mainPresenter = MainPresenter(view, marvelRepository)
        // When
        mainPresenter.onViewCreated()
        // Then
        assertEquals(someError, errorDisplayed)
    }

    @Test
    fun `When presenter is waiting for response, refresh is displayed`() {
        // Given
        val view = BaseMainView(refresh = false)
        val footballFanRepository = BaseFootballFanRepository(
                onGetCharacters = {
                    Single.fromCallable {
                        // Then
                        assertTrue(view.refresh)
                        Example.examplePostList
                    }
                }
        )
        val mainPresenter = MainPresenter(view, footballFanRepository)
        view.onShow = { _ ->
            // Then
            assertTrue(view.refresh)
        }
        // When
        mainPresenter.onViewCreated()
        // Then
        assertFalse(view.refresh)
    }

    private fun assertOnAction(action: MainPresenter.() -> Unit) = PresenterActionAssertion(action)

    private class PresenterActionAssertion(val actionOnPresenter: MainPresenter.() -> Unit) {

        fun thereIsSameListDisplayed() {
            // Given
            var displayedList: List<Post>? = null
            val view = BaseMainView(
                    onShow = { items -> displayedList = items},
                    onShowError = { fail()}
            )
            val marvelRepository = BaseFootballFanRepository(
                    onGetCharacters = { Single.just(Example.examplePostList) }
            )
            val mainPresenter = MainPresenter(view, marvelRepository)

            // When
            mainPresenter.actionOnPresenter()

            // Then
            assertEquals(Example.examplePostList, displayedList)
        }
    }
}
