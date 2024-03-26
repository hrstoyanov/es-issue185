package issue185.datamodel;

public record ElectronicAddress(String phone, String email, String webSite) {

    public final static class Builder implements issue185.utilities.Builder<ElectronicAddress> {

        private String phone;
        private String email;
        private String webSite;

        public Builder() {
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder webSite(String webSite) {
            this.webSite = webSite;
            return this;
        }

        @Override
        public ElectronicAddress build() {
            return new ElectronicAddress(phone, email, webSite);
        }

    }
}
