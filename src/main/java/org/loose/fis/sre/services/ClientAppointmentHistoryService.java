package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.loose.fis.sre.model.Appointment;

import java.util.Objects;

import static org.loose.fis.sre.services.AppointmentService.getAppointmentRepository;
import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class ClientAppointmentHistoryService {
    private static ObjectRepository<Appointment> clientAppointmentsRepository;
    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("client-appointments.db").toFile())
                .openOrCreate();

        clientAppointmentsRepository = database.getRepository(Appointment.class);
    }
    public static ObjectRepository<Appointment> getClientAppointmentsRepository() {
        return clientAppointmentsRepository;
    }
    static ObjectRepository<Appointment> appointmentRepository = getAppointmentRepository();
    public static void updateClientAppointmens(String client) {
        clientAppointmentsRepository.remove(ObjectFilters.ALL);
        for (Appointment appointment : appointmentRepository.find()){
            if (Objects.equals(appointment.getUsername(),client)){
                clientAppointmentsRepository.insert(new Appointment(appointment.getNr(), appointment.getUsername(),
                        appointment.getCarwash(), appointment.getnCoins(), appointment.getsTime(), appointment.getPayment()));
    }}}
}
