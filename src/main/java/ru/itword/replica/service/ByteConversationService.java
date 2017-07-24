package ru.itword.replica.service;

import org.springframework.stereotype.Service;

/**
 * Created by Itword on 23.07.2017.
 */
@Service
public class ByteConversationService {
    public Byte[] convertToBytes(byte[] bytes){
        Byte[] result = new Byte[bytes.length];
        for (int i = 0; i<bytes.length; i++){
            result[i] = bytes[i];
        }
        return result;
    }
    public byte[] convertToBytes(Byte[] bytes){
        byte[] result = new  byte[bytes.length];
        for (int i = 0; i<bytes.length; i++){
            result[i] = bytes[i];
        }
        return result;
    }
}
