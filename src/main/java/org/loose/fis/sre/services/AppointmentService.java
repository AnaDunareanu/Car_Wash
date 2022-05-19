package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.AppointmentNotAvailableException;
import org.loose.fis.sre.model.Appointment;


import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class AppointmentService {

    private static ObjectRepository<Appointment> appointmentRepository;
    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("appointment.db").toFile())
                .openOrCreate();

        appointmentRepository = database.getRepository(Appointment.class);
    }

    public static ObjectRepository<Appointment> getAppointmentRepository() {
        return appointmentRepository;
    }

    public static void addAppointment(String username, String carwash, Integer nCoins, Integer sTime) throws AppointmentNotAvailableException {
        checkAppointmentAvailable(carwash, sTime);
        int nr=1;
        for (Appointment app : appointmentRepository.find())
            nr++;
        appointmentRepository.insert(new Appointment(nr, username, carwash, nCoins, sTime));
    }
    private static void checkAppointmentAvailable(String carwash, Integer sTime) throws AppointmentNotAvailableException {
        for (Appointment app : appointmentRepository.find()) {
            if (Objects.equals(sTime,app.getsTime()) && Objects.equals(carwash,app.getCarwash()))
                throw new AppointmentNotAvailableException();
        }
    }
}
