package openfurry

import java.awt.image.*
import java.awt.geom.*
import javax.imageio.ImageIO

class ImagingService {

    static transactional = false

    def createImageUserObjectFiles(Person user, multiPartFile, File path) {
        BufferedImage original = ImageIO.read(multiPartFile.inputStream)

        def full = new File(path, f.getOriginalFilename())
        def sized = new File(path, "sized.${f.getOriginalFilename()}")
        def thumb = new File(path, "thumb.${f.getOriginalFilename()}")

        String format = ImageIO.getImageReaders(multiPartFile.inputStream).get(0).getFormatName()

        ImageIO.write(original, format, full)
        ImageIO.write(resizeImage(original, 400), format, sized)
        ImageIO.write(resizeImage(original, 100), format, thumb)

        [ full, sized, thumb ]
    }

    private static def resizeImage(originalImage, maxDimension) {
        Double widthScale = (double)maxDimension / (double)originalImage.width
        Double heightScale = (double)maxDimension / (double)originalImage.Height

        if (widthScale < 1.0d || heightScale < 1.0d) {
            Double scale = Math.min(widthScale, heightScale)
            def transform = new AffineTransform()
            transform.scale(scale, scale)
            def op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR)
            return op.filter(originalImage, null)
        } else {
            return originalImage
        }
    }
}
