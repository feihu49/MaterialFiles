/*
 * Copyright (c) 2024 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.files.provider.webdav.client

import at.bitfire.dav4jvm.HttpUtils
import at.bitfire.dav4jvm.Response
import at.bitfire.dav4jvm.property.CreationDate
import at.bitfire.dav4jvm.property.GetContentLength
import at.bitfire.dav4jvm.property.GetLastModified
import at.bitfire.dav4jvm.property.ResourceType
import java.time.Instant

val Response.creationTime: Instant?
    get() = this[CreationDate::class.java]?.creationDate
        ?.let { HttpUtils.parseDate(it)?.toInstant() }

val Response.isDirectory: Boolean
    get() = this[ResourceType::class.java]?.types?.contains(ResourceType.COLLECTION) == true

val Response.isSymbolicLink: Boolean
    get() = newLocation != null

val Response.lastModifiedTime: Instant?
    get() = this[GetLastModified::class.java]?.lastModified?.let { Instant.ofEpochMilli(it) }


val Response.size: Long
    get() = this[GetContentLength::class.java]?.contentLength ?: 0
