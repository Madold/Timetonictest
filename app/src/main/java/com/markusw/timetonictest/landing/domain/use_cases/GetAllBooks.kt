package com.markusw.timetonictest.landing.domain.use_cases

import com.markusw.timetonictest.landing.domain.model.Book
import com.markusw.timetonictest.landing.domain.repository.BooksRepository
import javax.inject.Inject

/**
 * Use case to get all books
 * @param booksRepository the repository to get the books
 * @return a list of books
 * @see Book
 * @see BooksRepository
 */
class GetAllBooks @Inject constructor(
    private val booksRepository: BooksRepository
) {
    /**
     * Get all books
     * @return a list of books
     */
    suspend operator fun invoke(): List<Book> {
        val books = booksRepository.getAllBooks().data?.allBooks?.books ?: return emptyList()
        val contacts = booksRepository.getAllBooks().data?.allBooks?.contacts ?: return emptyList()

        // If contacts list is empty then returns all the books
        if (contacts.isEmpty()) {
            return books.map { bookResponse ->
                Book(
                    name = bookResponse.ownerPrefs.title,
                    coverUrl = "https://timetonic.com${bookResponse.ownerPrefs.oCoverImg}"
                )
            }
        }

        // Otherwise, returns only the books that have at least one member in the contacts list
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