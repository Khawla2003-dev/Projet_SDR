package HealthSystem;


/**
* HealthSystem/HealthServiceOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from HealthMonitor.idl
* Thursday 01 May 2025 3:33:16 PM
*/

public interface HealthServiceOperations 
{
  void sendData (String patientId, float temperature, float heartRate, float spo2);
} // interface HealthServiceOperations
