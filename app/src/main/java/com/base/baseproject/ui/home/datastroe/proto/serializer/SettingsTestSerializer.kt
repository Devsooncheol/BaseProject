package com.base.baseproject.ui.home.datastroe.proto.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.base.baseproject.SettingsTest
import java.io.InputStream
import java.io.OutputStream

object SettingsTestSerializer : Serializer<SettingsTest> {
    override val defaultValue: SettingsTest = SettingsTest.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SettingsTest {
        return try {
            SettingsTest.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: SettingsTest, output: OutputStream) {
        t.writeTo(output)
    }
}