package dev.ogabek.kotlin.listener

import dev.ogabek.kotlin.model.Contact

interface SendContactListener {
    fun onSendContact(contact: Contact)
}