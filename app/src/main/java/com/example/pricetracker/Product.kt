package com.example.pricetracker

import java.lang.NullPointerException

class Product {
    var id: Int = 0
    var amazonURL: String? = null
    var camelURL: String? = null

    constructor(amazonUrl: String, camelUrl : String) {
        this.amazonURL = amazonUrl
        this.camelURL = camelUrl
    }
}