CREATE INDEX idx_clinics_location_clinic_id ON clinics (location, id);
CREATE INDEX idx_clinics_location ON clinics (location);

CREATE INDEX idx_availability_clinic_id ON availability (clinic_id);
CREATE INDEX idx_availability_date ON availability (date);
CREATE INDEX idx_availability_doctor_id ON availability (doctor_id);
CREATE INDEX idx_availability_clinic_doctor_date ON availability (clinic_id, doctor_id, date);
CREATE INDEX idx_availability_schedule_template_id ON availability(schedule_template_id);

CREATE INDEX idx_schedule_template_clinic_id ON schedule_template(clinic_id);
CREATE INDEX idx_schedule_template_constraint_id ON schedule_template(constraint_id);
CREATE INDEX idx_schedule_template_start_time ON schedule_template(start_time);
