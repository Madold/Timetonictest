package com.markusw.timetonictest.network.data.remote.responses

data class AllBooks(
    val contacts: List<ContactResponse>,
    val books: List<BookResponse>,
)
