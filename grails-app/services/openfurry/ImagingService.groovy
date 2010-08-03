package openfurry

import java.awt.image.*
import java.awt.geom.*
import javax.imageio.ImageIO
import javax.imageio.stream.ImageInputStream
import org.andrill.util.image.*

class ImagingService {

    static transactional = false

    def createImageUserObjectFiles(Person user, multiPartFile, File path, Long time) {

        def full = new File(path, "${time}.${user.username}_${multiPartFile.getOriginalFilename()}")
        def sized = new File(path, "sized.${time}.${user.username}_${multiPartFile.getOriginalFilename()}")
        def thumb = new File(path, "thumb.${time}.${user.username}_${multiPartFile.getOriginalFilename()}")

        multiPartFile.transferTo(full)
        resizeImage(full, 1280, full)
        resizeImage(full, 400, sized)
        resizeImage(full, 100, thumb)

        [ full, sized, thumb ]
    }

    def createThumbnailFile(multiPartFile, File dest) {
        multiPartFile.transferTo(dest)

        resizeImage(dest, 100, dest)

        dest
    }

    private resizeImage(inFile, maxDimension, outFile) {
        BufferedImage originalImage = ImageIO.read(inFile)
        Double widthScale = (double)maxDimension / (double)originalImage.width
        Double heightScale = (double)maxDimension / (double)originalImage.height

        ImageMagick convert = new ImageMagick();
        if (widthScale < 1.0d || heightScale < 1.0d) {
            Double scale = Math.min(widthScale, heightScale)
            convert.in(inFile).option("-resize", "${scale * 100}%").run(inFile, outFile)
            log.debug "Ran convert ${inFile.getCanonicalPath()} -resize ${scale * 100}% ${outFile.getCanonicalPath()}"
        } else {
            convert.in(inFile).run(inFile, outFile)
        }
    }
}
