package dev.eric.binaria.viewmodel.state


data class ExchangeRatesState(
    val isLoading: Boolean = false,
    val kes: Double = 0.0,
    val ngn: Double = 0.0,
    val tzs: Double = 0.0,
    val ugx: Double = 0.0,
    val base: String = "",
    val error: String? = null,
    val country: String = "",
    val currency: String = "",
    val countryPrompt: String = "",
    val countryCode: String = "+254",
    val firstName: String = "",
    val firstNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val amount: String = "",
    val amountError: String = "",
    val amountInBinary: String = "",
    val prefix: String = "+254",
    val phone: String = "",
    val maxPhoneLength: Int = 0,
    val isSendButtonActive: Boolean = false
) {
    fun toMap(): Map<String, Double> {
        return mapOf(
            "kes" to kes,
            "ngn" to ngn,
            "tzs" to tzs,
            "ugx" to ugx
        )
    }
}
