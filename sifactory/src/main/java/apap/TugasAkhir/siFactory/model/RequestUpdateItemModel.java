package apap.TugasAkhir.siFactory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
// @Setter @Getter
@Entity
@Table(name = "request_update_item")
public class RequestUpdateItemModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRequestUpdateItem;

    @NotNull
    @Column(name = "id_item", nullable = false)
    private String idItem;

    @NotNull
    @Column(name = "id_kategori", nullable = false)
    private int idKategori;

    @NotNull
    @Column(name = "tambahan_stok", nullable = false)
    private int tambahanStok;

    @NotNull
    @Column(name = "tanggal_request", nullable = false)
    @DateTimeFormat(pattern = "yyyy MM dd")
    private LocalDate tanggalRequest;

    @NotNull
    @Column(name = "id_cabang", nullable = false)
    private int idCabang;

    @NotNull
    @Column(name = "executed", nullable = false, columnDefinition = "boolean default false")
    private boolean executed;

    public int getIdRequestUpdateItem() {
        return idRequestUpdateItem;
    }

    public void setIdRequestUpdateItem(int idRequestUpdateItem) {
        this.idRequestUpdateItem = idRequestUpdateItem;
    }

    // Relasi dengan ProduksiModel
    @OneToOne(mappedBy = "requestUpdateItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProduksiModel produksiModel;

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public int getTambahanStok() {
        return tambahanStok;
    }

    public void setTambahanStok(int tambahanStok) {
        this.tambahanStok = tambahanStok;
    }

    public LocalDate getTanggalRequest() {
        return tanggalRequest;
    }

    public void setTanggalRequest(LocalDate tanggalRequest) {
        this.tanggalRequest = tanggalRequest;
    }

    public int getIdCabang() {
        return idCabang;
    }

    public void setIdCabang(int idCabang) {
        this.idCabang = idCabang;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public ProduksiModel getProduksiModel() {
        return produksiModel;
    }

    public void setProduksiModel(ProduksiModel produksiModel) {
        this.produksiModel = produksiModel;
    }

    public DeliveryModel getDeliveryModel() {
        return deliveryModel;
    }

    public void setDeliveryModel(DeliveryModel deliveryModel) {
        this.deliveryModel = deliveryModel;
    }

    // Relasi dengan DeliveryModel
    @OneToOne(mappedBy = "requestUpdateItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DeliveryModel deliveryModel;

}
