package ad.food2.Dto;

public class DtoClient {

    private String name;
    private String phone;

    public DtoClient() {
    }

    public DtoClient(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
