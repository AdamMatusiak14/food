package ad.food2.Dto;

import java.util.Objects;

import ad.food2.object.Status;

public class DtoStatus {

    private Long id;
    private Status status;

    public DtoStatus() {
    }

    public DtoStatus(Long id, Status status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // @Override
    // public boolean equals(Object o) {
    // if (this == o)
    // return true;
    // if (o == null || getClass() != o.getClass())
    // return false;
    // DtoStatus dtoStatus = (DtoStatus) o;
    // return status == dtoStatus.status;
    // }

    // @Override
    // public int hashCode() {
    // return Objects.hash(status);
    // }

}
