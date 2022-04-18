--liquibase formatted sql

--changeset igor:1

ALTER TABLE IF EXISTS add_pay
    ADD CONSTRAINT FK_add_pay_add_pay_type FOREIGN KEY (add_pay_type_id) REFERENCES add_pay_type;
ALTER TABLE IF EXISTS add_pay_fund
    ADD CONSTRAINT FK_add_pay_fund_add_pay_type FOREIGN KEY (add_pay_type_id) REFERENCES add_pay_type;
ALTER TABLE IF EXISTS add_pay_fund
    ADD CONSTRAINT FK_add_pay_fund_calc_settings FOREIGN KEY (calc_settings_id) REFERENCES calc_settings;
ALTER TABLE IF EXISTS add_pay_result
    ADD CONSTRAINT FK_add_pay_result_add_pay FOREIGN KEY (add_pay_id) REFERENCES add_pay;
ALTER TABLE IF EXISTS add_pay_result
    ADD CONSTRAINT FK_add_pay_result_basic_norms FOREIGN KEY (basic_norms_id) REFERENCES basic_norms;
ALTER TABLE IF EXISTS add_pay_result
    ADD CONSTRAINT FK_add_pay_result_staff_list FOREIGN KEY (staff_list_id) REFERENCES staff_list;
ALTER TABLE IF EXISTS add_pay_result
    ADD CONSTRAINT FK_add_pay_result_time_sheet FOREIGN KEY (time_sheet_id) REFERENCES time_sheet;
ALTER TABLE IF EXISTS percent_salary_result
    ADD CONSTRAINT FK_percent_salary_result_percent_salary FOREIGN KEY (percent_salary_id) REFERENCES percent_salary;
ALTER TABLE IF EXISTS percent_salary_result
    ADD CONSTRAINT FK_percent_salary_result_staff_list FOREIGN KEY (staff_list_id) REFERENCES staff_list;
ALTER TABLE IF EXISTS percent_salary_result
    ADD CONSTRAINT FK_percent_salary_result_time_sheet FOREIGN KEY (time_sheet_id) REFERENCES time_sheet;
ALTER TABLE IF EXISTS staff_list
    ADD CONSTRAINT FK_staff_list_people FOREIGN KEY (people_id) REFERENCES people;
ALTER TABLE IF EXISTS staff_list
    ADD CONSTRAINT FK_staff_list_position FOREIGN KEY (position_id) REFERENCES position;
ALTER TABLE IF EXISTS time_sheet
    ADD CONSTRAINT FK_time_sheet_calc_settings FOREIGN KEY (calc_settings_id) REFERENCES calc_settings;
ALTER TABLE IF EXISTS time_sheet
    ADD CONSTRAINT FK_time_sheet_people FOREIGN KEY (people_id) REFERENCES people;
ALTER TABLE IF EXISTS users
    ADD CONSTRAINT FK_users_people FOREIGN KEY (people_id) REFERENCES people;