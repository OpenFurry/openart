package openfurry

import java.awt.image.*
import java.awt.geom.*
import javax.imageio.ImageIO

class ImagingService {

    static transactional = false

    def createImageUserObjectFiles(Person user, ImageUserObject image, multiPartFile) {
        BufferedImage original = ImageIO.read(multiPartFile.inputStream)

        new File(grailsApplication.config.images.location.toString()).mkdirs()
        def full = new File(grailsApplication.config.images.location.toString() + File.separatorChar + "full" + File.separatorChar + f.getOriginalFilename())
        def sized = new File(grailsApplication.config.images.location.toString() + File.separatorChar + "sized" + File.separatorChar + f.getOriginalFilename())
        def thumb = new File(grailsApplication.config.images.location.toString() + File.separatorChar + "thumb" + File.separatorChar + f.getOriginalFilename())

        [ full, sized, thumb ]
    }

    private static def resizeImage(originalImage, maxDimension) {
        Double widthScale = (double)maxDimension / (double)originalImage.width
        Double heightScale = (double)maxDimension / (double)originalImage.Height
        Double scale = Math.min(widthScale, heightScale)

        def transform = new AffineTransform()
        transform.scale(widthScale, heightScale)
        def op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR)
        return op.filter(originalImage, null)
    }
}
