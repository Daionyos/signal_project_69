package com.data_management;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class DataStorageTest {

    private DataStorage storage;

    @BeforeEach
    void setUp() {
        storage = new DataStorage();
    }

    @Test
    void testAddAndGetRecords() {
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(2, records.size(), "Two records should be retrieved");
        assertEquals(100.0, records.get(0).getMeasurementValue(), "First record should have measurement value 100.0");
        assertEquals(200.0, records.get(1).getMeasurementValue(), "Second record should have measurement value 200.0");
    }

    @Test
    void testGetRecordsWithNoData() {
        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
        assertTrue(records.isEmpty(), "No records should be retrieved for a non-existent patient");
    }

    @Test
    void testAddAndRetrieveMultiplePatients() {
        storage.addPatientData(1, 120.0, "HeartRate", 1714376789050L);
        storage.addPatientData(2, 80.0, "BloodPressure", 1714376789051L);

        List<PatientRecord> recordsPatient1 = storage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(1, recordsPatient1.size(), "One record should be retrieved for patient 1");
        assertEquals(120.0, recordsPatient1.get(0).getMeasurementValue(), "Patient 1 record should have measurement value 120.0");

        List<PatientRecord> recordsPatient2 = storage.getRecords(2, 1714376789050L, 1714376789052L);
        assertEquals(1, recordsPatient2.size(), "One record should be retrieved for patient 2");
        assertEquals(80.0, recordsPatient2.get(0).getMeasurementValue(), "Patient 2 record should have measurement value 80.0");
    }

    @Test
    void testGetAllPatients() {
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(2, 200.0, "WhiteBloodCells", 1714376789051L);

        List<Patient> patients = storage.getAllPatients();
        assertEquals(2, patients.size(), "Two patients should be retrieved");

        assertTrue(patients.stream().anyMatch(p -> p.getPatientId() == 1), "Patient with ID 1 should exist");
        assertTrue(patients.stream().anyMatch(p -> p.getPatientId() == 2), "Patient with ID 2 should exist");
    }
}
