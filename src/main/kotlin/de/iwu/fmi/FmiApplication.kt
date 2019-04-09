package de.iwu.fmi

import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.IOFileFilter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.io.File
import javax.persistence.*

// Spring Boot Application
@EnableJpaRepositories(basePackages = ["de.iwu.fmi"])
@SpringBootApplication
class FmiApplication

// Repository
@Repository
interface IWUFileRepository : CrudRepository<IWUFile, String> {}

// PoKo
@Entity
@Table(name = "filesdemo")
data class IWUFile(
        @Column(name = "PrimaryKey")
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: String,

        @Column(name = "path")
        var path: String,

        @Column(name = "filename")
        var filename: String)

// Just a run-once then throw-away cli tool to insert specific filename patterns (redacted 🤫) into a Filemaker Database (see filesdemo.fmp12)
fun main(args: Array<String>) {

    // Start to scan for files in ...
    val BASEDIR = "."

    val repository = runApplication<FmiApplication>().getBean(IWUFileRepository::class.java)

    val fileDirFilter: IOFileFilter = object : IOFileFilter {
        override fun accept(file: File?): Boolean {
            if (file!!.isFile) {
                val entry = IWUFile("", file.path, file.name)
                repository.save(entry)
            }
            return true
        }

        override fun accept(dir: File?, name: String?): Boolean {
            return true
        }
    }

    FileUtils.listFilesAndDirs(File(BASEDIR), fileDirFilter, fileDirFilter)

    println("All done ✌️")
}
