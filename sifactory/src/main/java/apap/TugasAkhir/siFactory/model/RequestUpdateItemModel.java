package apap.TugasAkhir.siFactory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "request_update_item")
public class RequestUpdateItemModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRequestUpdateItem;

    @NotNull
    @Column(name = "id_item", nullable = false)
    private String idItem;

    @NotNull
    @Column(name = "id_kategori", nullable = false)
    private Long idKategori;

    @NotNull
    @Column(name = "tambahan_stok", nullable = false)
    private Long tambahanStok;

    @NotNull
    @Column(name = "tanggal_request", nullable = false)
    @DateTimeFormat(pattern = "yyyy MM dd")
    private Date tanggalRequest;

    @NotNull
    @Column(name = "id_cabang", nullable = false)
    private Long idCabang;

    @NotNull
    @Column(name = "executed", nullable = false)
    private boolean executed;

    //Relasi dengan Produksi
    @OneToOne(mappedBy = "request_update_item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProduksiModel produksiModel;

    //Relasi dengan Produksi
    @OneToOne(mappedBy = "request_update_item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DeliveryModel deliveryModel;
    
}
