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
@Setter @Getter
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

    // Relasi dengan ProduksiModel
    @OneToOne(mappedBy = "requestUpdateItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProduksiModel produksiModel;

    // Relasi dengan DeliveryModel
    @OneToOne(mappedBy = "requestUpdateItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DeliveryModel deliveryModel;

}
