package apap.TugasAkhir.siFactory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "produksi")
public class ProduksiModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduksi;

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
    @Column(name="tanggal_produksi", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalProduksi;

    //Relasi dengan PegawaiModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pegawai", referencedColumnName = "idPegawai", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PegawaiModel pegawai;

    //Relasi dengan RequestUpdateItemModel
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_request_update_item", referencedColumnName = "idRequestUpdateItem")
    private RequestUpdateItemModel requestUpdateItem;

    //Relasi dengan MesinModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_mesin", referencedColumnName = "idMesin", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MesinModel mesin;
}
