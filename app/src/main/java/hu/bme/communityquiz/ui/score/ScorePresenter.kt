package hu.bme.communityquiz.ui.score

import hu.bme.communityquiz.interactor.DataBaseInteractor
import hu.bme.communityquiz.interactor.ServerInteractor
import hu.bme.communityquiz.network.NetworkModule
import hu.bme.communityquiz.ui.Presenter
import javax.inject.Inject

class ScorePresenter @Inject constructor(
        private val dataBaseInteractor: DataBaseInteractor,
        private val serverInteractor: ServerInteractor
):Presenter<ScoreScreen>() {
    fun loadScorePairs() = dataBaseInteractor.loadScores()
    fun loadGlobalScores() = serverInteractor.listGlobalScores()
}