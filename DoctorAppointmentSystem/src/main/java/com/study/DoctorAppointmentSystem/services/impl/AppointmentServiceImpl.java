package com.study.DoctorAppointmentSystem.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.DoctorAppointmentSystem.dtos.AppointmentRequestDto;
import com.study.DoctorAppointmentSystem.dtos.AppointmentResponseDto;
import com.study.DoctorAppointmentSystem.entity.Appointment;
import com.study.DoctorAppointmentSystem.entity.Doctor;
import com.study.DoctorAppointmentSystem.entity.DoctorSlots;
import com.study.DoctorAppointmentSystem.entity.Patient;
import com.study.DoctorAppointmentSystem.entity.User;
import com.study.DoctorAppointmentSystem.enums.AppointmentStatus;
import com.study.DoctorAppointmentSystem.enums.SlotsStatus;
import com.study.DoctorAppointmentSystem.repository.AppointmentRepository;
import com.study.DoctorAppointmentSystem.repository.DoctorRepository;
import com.study.DoctorAppointmentSystem.repository.DoctorSlotsRepository;
import com.study.DoctorAppointmentSystem.repository.PatientRepository;
import com.study.DoctorAppointmentSystem.repository.UserRepositories;
import com.study.DoctorAppointmentSystem.services.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private UserRepositories userRepositories;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorSlotsRepository doctorSlotsRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AppointmentResponseDto bookAppointment(Integer userId, Integer slotId) {

		User user = userRepositories.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		DoctorSlots doctorSlots = doctorSlotsRepository.findById(slotId)
				.orElseThrow(() -> new RuntimeException("Slot not found"));

		Doctor doctor = doctorSlots.getDoctor();
		Patient patient = user.getPatient();

		boolean slotAvailable = doctorSlots.getSlotStatus().equals(SlotsStatus.AVAILABLE);

		if (!slotAvailable) {
			throw new RuntimeException("Slot Already Booked");
		}

		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(doctorSlots.getSlotDate());
		appointment.setAppointmentTime(doctorSlots.getStartTime());
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setDoctorSlots(doctorSlots);
		appointment.setStatus(AppointmentStatus.Pending);

		Appointment saveAppointment = appointmentRepository.save(appointment);

		doctorSlots.setSlotStatus(SlotsStatus.BOOKED);
		doctorSlotsRepository.save(doctorSlots);

		AppointmentResponseDto responseDto = modelMapper.map(saveAppointment, AppointmentResponseDto.class);

		responseDto.setPatientName(patient.getUser().getName());
		responseDto.setDocId(doctor.getDocId());
		responseDto.setDoctorName(doctor.getUser().getName());
		responseDto.setSpecialization(doctor.getSpecialization());
		
		return responseDto;
	}

	@Override
	public AppointmentResponseDto getAppointmentById(Integer id) {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Id not found"));

		AppointmentResponseDto responseDto = modelMapper.map(appointment, AppointmentResponseDto.class);

		responseDto.setPatientName(appointment.getPatient().getUser().getName());
		responseDto.setDoctorName(appointment.getDoctor().getUser().getName());

		return responseDto;
	}

	@Override
	public List<AppointmentResponseDto> getAllAppointments() {

		List<Appointment> appointments = appointmentRepository.findAll();

		List<AppointmentResponseDto> listOfAppointments = appointments.stream().map((a) -> {
			AppointmentResponseDto responseDto = modelMapper.map(a, AppointmentResponseDto.class);
			responseDto.setPatientName(a.getPatient().getUser().getName());
			responseDto.setDoctorName(a.getDoctor().getUser().getName());
			return responseDto;
		}).toList();

		return listOfAppointments;
	}

	@Override
	public AppointmentResponseDto updateAppointment(Integer id, AppointmentRequestDto appointmentRequestDto) {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Appointment Id not found"));
		appointment.setAppointmentDate(appointmentRequestDto.getAppointmentDate());
		appointment.setAppointmentTime(appointmentRequestDto.getAppointmentTime());

		Appointment updatedAppointment = appointmentRepository.save(appointment);
		AppointmentResponseDto responseDto = modelMapper.map(updatedAppointment, AppointmentResponseDto.class);
		responseDto.setPatientName(appointment.getPatient().getUser().getName());
		responseDto.setDoctorName(appointment.getDoctor().getUser().getName());
		return responseDto;
	}

	@Override
	public void deleteAppointment(Integer id) {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Id not found"));
		appointmentRepository.delete(appointment);
	}

	@Override
	public List<AppointmentResponseDto> getAllAppointmentsOfPatientById(Integer id) {
		List<Appointment> appointments = appointmentRepository.findByPatientPatientId(id);
		List<AppointmentResponseDto> listOfAllPatientAppointments = appointments.stream().map((a) -> {
			AppointmentResponseDto responseDto = modelMapper.map(a, AppointmentResponseDto.class);
			responseDto.setDoctorName(a.getDoctor().getUser().getName());
			responseDto.setSpecialization(a.getDoctor().getSpecialization());
			return responseDto;
		}).toList();
		return listOfAllPatientAppointments;
	}

	@Override
	public List<AppointmentResponseDto> getAllAppointmentsOfDoctorById(Integer id) {
		List<Appointment> appointments = appointmentRepository.findByDoctorDocId(id);
		List<AppointmentResponseDto> listOfAllDoctorAppointments = appointments.stream().map((a) -> {
			AppointmentResponseDto responseDto = modelMapper.map(a, AppointmentResponseDto.class);
			responseDto.setPatientName(a.getPatient().getUser().getName());
			return responseDto;
		}).toList();
		return listOfAllDoctorAppointments;
	}

	@Override
	public AppointmentResponseDto acceptAppointment(Integer id) {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Appointment Id not found"));
		appointment.setStatus(AppointmentStatus.Booked);
		appointmentRepository.save(appointment);
		AppointmentResponseDto responseDto = modelMapper.map(appointment, AppointmentResponseDto.class);
		responseDto.setPatientName(appointment.getPatient().getUser().getName());
		responseDto.setDoctorName(appointment.getDoctor().getUser().getName());
		return responseDto;
	}

	@Override
	public AppointmentResponseDto rejectAppointment(Integer id) {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Appointment id not found"));
		appointment.setStatus(AppointmentStatus.Rejected);
		appointmentRepository.save(appointment);
		AppointmentResponseDto responseDto = modelMapper.map(appointment, AppointmentResponseDto.class);
		responseDto.setPatientName(appointment.getPatient().getUser().getName());
		responseDto.setDoctorName(appointment.getDoctor().getUser().getName());
		return responseDto;
	}

	@Override
	public AppointmentResponseDto cancelAppointment(Integer id) {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Appointment id not found"));
		appointment.setStatus(AppointmentStatus.Cancelled);
		appointmentRepository.save(appointment);
		AppointmentResponseDto responseDto = modelMapper.map(appointment, AppointmentResponseDto.class);
		responseDto.setPatientName(appointment.getPatient().getUser().getName());
		responseDto.setDoctorName(appointment.getDoctor().getUser().getName());
		return responseDto;
	}

}
