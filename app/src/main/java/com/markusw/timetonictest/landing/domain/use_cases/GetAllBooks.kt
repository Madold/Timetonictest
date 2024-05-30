package com.markusw.timetonictest.landing.domain.use_cases

import com.markusw.timetonictest.landing.domain.model.Book
import com.markusw.timetonictest.landing.domain.repository.BooksRepository
import javax.inject.Inject

class GetAllBooks @Inject constructor(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(): List<Book> {
        val books = booksRepository.getAllBooks().data?.allBooks?.books ?: return emptyList()
        val contacts = booksRepository.getAllBooks().data?.allBooks?.contacts ?: return emptyList()

        if (contacts.isEmpty()) {
            return books.map { bookResponse ->
                Book(
                    name = bookResponse.ownerPrefs.title,
                    coverUrl = "https://timetonic.com${bookResponse.ownerPrefs.oCoverImg}"
                )
            }
        }

        val contactsUc = contacts.map { contact ->
            contact.u_c
        }

        return books.filter { bookResponse ->
            return@filter bookResponse.members.any {
                it.u_c in contactsUc
            }
        }.map {
            Book(
                name = it.ownerPrefs.title,
                coverUrl = "https://timetonic.com${it.ownerPrefs.oCoverImg}"
            )
        }

    }

}