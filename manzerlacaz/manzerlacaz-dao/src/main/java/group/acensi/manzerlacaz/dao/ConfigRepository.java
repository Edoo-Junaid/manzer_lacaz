package group.acensi.manzerlacaz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group.acensi.manzerlacaz.entities.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
    @Query("SELECT value FROM Config WHERE name='time'")
    public String presetTime();


    @Query("SELECT id FROM Config WHERE name='time'")
    public long getConfigurationId();
    

    @Query("SELECT EXISTS(SELECT 1 FROM Config WHERE name=?1)")
    public boolean checkIfConfigExists(String name);

}
