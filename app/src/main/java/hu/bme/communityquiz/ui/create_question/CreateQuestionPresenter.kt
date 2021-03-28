package hu.bme.communityquiz.ui.create_question

import hu.bme.communityquiz.interactor.ServerInteractor
import hu.bme.communityquiz.model.Question
import hu.bme.communityquiz.network.NetworkModule
import hu.bme.communityquiz.ui.Presenter
import javax.inject.Inject

class CreateQuestionPresenter @Inject constructor(
        private val serverInteractor: ServerInteractor
): Presenter<CreateQuestionScreen>() {
    fun saveQuestion(value:Question) = serverInteractor.saveQuestion(value)
}