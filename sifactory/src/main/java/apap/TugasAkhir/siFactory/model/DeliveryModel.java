package apap.TugasAkhir.siFactory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "delivery")
public class DeliveryModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDelivery;

    @NotNull
    @Column(name = "id_cabang", nullable = false)
    private Integer idCabang;

    @NotNull
    @Column(name = "tanggal_dibuat", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalDibuat;

    @Column(name = "tanggal_dikirim")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalDikirim;

    @Column(name = "sent", nullable = false)
    private Boolean sent;

    // Relation with PegawaiModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_kurir", referencedColumnName = "idPegawai", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PegawaiModel pegawai;

    // Relation with RequestUpdateItemModel
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_request_update_item", referencedColumnName = "idRequestUpdateItem")
    private RequestUpdateItemModel requestUpdateItem;
}