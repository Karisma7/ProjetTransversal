package com.AventixPay.Aventix.service.serviceImpl;

import com.AventixPay.Aventix.service.RFIDService;
import com.AventixPay.Aventix.service.UserService;
import com.fazecast.jSerialComm.SerialPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


    @Service
    public class RFIDServiceImpl implements RFIDService {

        //private static final Logger logger = LoggerFactory.getLogger(RFIDServiceImpl.class);
        private static final String PORT_NAME = "COM5"; // port de lecture
        private static final int BAUD_RATE = 115200;
        private static final int TIMEOUT_MS = 5000; // Temps d'attente max
        private static final int READ_TIMEOUT = 2000; // Délai d'attente de 2000 ms
        private static final int TIMEOUT_READ_BLOCKING = 0;
    @Autowired
        UserService userService;
        @Override
        public String readSerialNumberFromRFID() {
            SerialPort serialPort = SerialPort.getCommPort(PORT_NAME);
            serialPort.setBaudRate(BAUD_RATE);
            serialPort.setNumDataBits(8);
            serialPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
            serialPort.setParity(SerialPort.NO_PARITY);
            serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, READ_TIMEOUT, 0);

            if (!serialPort.openPort()) {
                System.err.println("Impossible d'ouvrir le port série.");
                return null;
            }
            //connexion au port serie pour recuperer l'UID
            try {
                byte[] buffer = new byte[1024];
                int numRead = serialPort.readBytes(buffer, buffer.length);

                if (numRead > 0) {
                    byte[] serialNumber = new byte[numRead];
                    System.arraycopy(buffer, 0, serialNumber, 0, numRead);
                    return bytesToHex(serialNumber); //return UID en hexadecimal en appellant la methode bytesToHex
                } else {
                    System.err.println("Aucune donnée reçue du port.");
                    return null;
                }
            } catch (Exception ex) {
                System.err.println("Erreur de communication série : " + ex.getMessage());
                return null;
            } finally {
                serialPort.closePort();
            }
        }
        //methode pour convertir l'UID en hexadecimal
        private String bytesToHex(byte[] bytes) {
            StringBuilder hexString = new StringBuilder();
            for (byte b : bytes) {
                hexString.append(String.format("%02X", b));
            }
            return hexString.toString();
        }

    }

