package apap.TugasAkhir.siFactory.model;

import apap.TugasAkhir.siFactory.model.RoleModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "pegawai")
public class PegawaiModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPegawai;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDateTime tanggalLahir;

    @NotNull
    @Size(max = 50)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(max = 50)
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "counter", nullable = false)
    private Integer counter;

    @NotNull
    @Lob
    @Column(name = "password", nullable = false)
    private String password;

    //Relasi dengan Role
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RoleModel role;

    //Relasi dengan Produksi
    @OneToMany(mappedBy = "idProduksi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProduksiModel> listProduksi;

    //Relasi dengan Delivery
    @OneToMany(mappedBy = "idDelivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DeliveryModel> listDelivery;
}
