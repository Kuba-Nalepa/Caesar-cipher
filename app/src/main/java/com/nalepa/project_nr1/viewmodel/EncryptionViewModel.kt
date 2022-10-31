package com.nalepa.project_nr1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class EncryptionViewModel: ViewModel() {

    private val chars = "AĄBCĆDEĘFGHIJKLŁMNŃOÓPQRSŚTUVWXYZŹŻ"
    private var input: String = ""
    private var isEncryptionEnabled = true

    private var _offset = MutableLiveData(0)
    private var _outputMessage = MutableLiveData ("Your text is showing here")

    val offset: LiveData<Int> = _offset
    var outputMessage: LiveData<String> = _outputMessage


    fun increaseOffset()
    {
        _offset.value = _offset.value?.plus(1)
        mainLogic()
    }

    fun decreaseOffset()
    {
        if(_offset.value!! > 0 )
        {
            _offset.value = _offset.value?.minus(1)
            mainLogic()
        }
    }

    fun setOutputMessage(input: String) {
        this.input = input
        mainLogic()
    }

    private fun mainLogic() {
        var output = input

        output = output.filter { chars.contains(it, true) }.map{
            var newIndex: Int
            if(isEncryptionEnabled) {
                newIndex = chars.indexOf(it,0,true) + _offset.value!!
                if (newIndex > chars.length - 1) {
                    newIndex -= chars.length - 1
                }
            } else {
                newIndex = chars.indexOf(it,0,true) - _offset.value!!
                if (newIndex < 0) {
                    newIndex += chars.length  -1
                }
            }

            chars[newIndex]

        }.joinToString("").lowercase()

        _outputMessage.value = output
    }


    fun toggleEncryption(toggle: Boolean) {
        isEncryptionEnabled = toggle
        mainLogic()
    }
}