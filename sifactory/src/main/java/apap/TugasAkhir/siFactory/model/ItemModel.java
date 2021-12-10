package apap.TugasAkhir.siFactory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="item")
public class ItemModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    @NotNull
    @Size(max=50)
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name="harga", nullable = false)
    private Integer harga;

    @NotNull
    @Column(name="stok", nullable = false)
    private Integer stok;

    @NotNull
    @Size(max=100)
    @Column(name="kategori", nullable = false)
    private String kategori;
}
