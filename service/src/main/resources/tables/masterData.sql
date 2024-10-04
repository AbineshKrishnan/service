INSERT INTO constraints (key, name, colour_code, is_deleted) VALUES
('1ST PO', 'First Day Post OP', '#CCFFCC', false),
('ACUITY', 'Accuity Booking Online', '#FFFFFF', false),
('ANAESTHE', 'Anaesthetist', '#D5A6BD', false),
('ASHEYE', 'Ashfield Eye Clinic Surgery', '#00B050', false),
('BALACHART', 'GIVE DR BALA SX CHARTS', '#C9A3D6', false),
('BOTOX', 'Botox', '#FFFFFF', false),
('BREAK', 'BREAK - DO NOT BOOK', '#FF0000', false),
('CAIRS', 'Cairs', '#A4C2F4', false),
('CDSSX', 'CDS Surgery', '#FFFFFF', false),
('CONVURRE', 'Please check for an extra ortho list', '#FFEB9C', false),
('CONS', 'Consultation', '#FFFFFF', false),
('COUNSELLING', 'Counselling', '#FFFFFF', false),
('DIATBETES', 'Diabetes Screenings', '#FFEB9C', false),
('DO NOT BOOK', 'DO NOT BOOK', '#FF0000', false),
('DOC NOT HERE', 'Doc Not Here - Move Patients', '#F4CCCC', false),
('DRY EYE CLIN', 'Dry eye clinic', '#FFFFFF', false);

INSERT INTO plan (key, name, is_deleted)
VALUES
('AKB', 'Bilateral AK', false),
('AKL', 'LAK', false),
('AKR', 'RAK', false),
('ASCAN', 'ASCAN', false),
('ATAVASB', 'Bilateral Avastin', false),
('AVASTINL', 'L Eye AVASTIN', false),
('AVASTINR', 'R Eye AVASTIN', false),
('BAERVELDTL', 'L BAERVELDT TUBE', false),
('BAERVELDTR', 'R BAERVELDT TUBE', false),
('BC', 'Bilateral Clairs', false),
('BEOVUB', 'Bilateral BEOVU', false),
('BEOVUL', 'L BEOVU', false),
('BEOVUR', 'R BEOVU', false),
('BILATBLEPHL', 'Bilateral Blepharoplasty', false),
('BLEPH2', 'Blephex & Blepharosteam Treatment', false),
('BLEPHL', 'L Blepharoplasty', false);


INSERT INTO reasons (reason, is_deleted)
VALUES
('Initial general consult', false),
('Followup consultation', false),
('Consultation with new referral', false),
('Surgery', false),
('Initial refractive consult', false),
('Initial Refractive ACUITY', false),
('Initial dry eye consult', false),
('Consultation (Initial)', false),
('Initial retinal consult', false),
('Initial Public Consult', false),
('Initial Cataract Consult', false),
('Refractive Screening', false),
('FREE POST OP 1 DAY', false),
('FREE POST OP 1 WEEK', false),
('BULK BILL PO 1 MONTH', false),
('BULK BILL PO 3 MONTH', false);

INSERT INTO master_data ("key", value)
VALUES
    ('appointment_status', 'Appointment Request'),
    ('appointment_status', 'Call Back Request'),
    ('appointment_status', 'Scheduled'),
    ('appointment_status', 'Completed'),
    ('appointment_status', 'Cancelled');
