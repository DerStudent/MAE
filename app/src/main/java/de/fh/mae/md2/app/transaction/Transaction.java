package de.fh.mae.md2.app.transaction;

import java.util.Date;

import de.fh.mae.md2.app.Category.Category;

public class Transaction {

        private String value;

        private Category category;

        private String note;

        private Date date;

        public Transaction(String value, Category category, String note, Date date){
            this.value = value;
            this.category = category;
            this.note = note;
            this.date = date;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
}