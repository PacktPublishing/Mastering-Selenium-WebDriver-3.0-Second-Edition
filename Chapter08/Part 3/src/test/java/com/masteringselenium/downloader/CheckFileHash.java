package com.masteringselenium.downloader;

import com.masteringselenium.downloader.HashType;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CheckFileHash {

    public static String generateHashForFileOfType(File fileToCheck, HashType hashType) throws Exception {
        if (!fileToCheck.exists()) throw new FileNotFoundException(fileToCheck + " does not exist!");

        switch (hashType) {
            case MD5:
                return DigestUtils.md5Hex(new FileInputStream(fileToCheck));
            case SHA1:
                return DigestUtils.sha1Hex(new FileInputStream(fileToCheck));
            default:
                throw new UnsupportedOperationException(hashType.toString() + " hash type is not supported!");
        }
    }
}