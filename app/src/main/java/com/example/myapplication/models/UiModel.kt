package com.example.myapplication.models

sealed class UiModel {
    data class HorseItem(val horse: Horse) : UiModel()
    data class SeparatorItem(val description: String) : UiModel()
}
