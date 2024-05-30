package com.markusw.timetonictest.network.data.remote.responses

data class AllBooksResponse(
    val contacts: List<ContactResponse>,
    val books: List<BookResponse>,
)
