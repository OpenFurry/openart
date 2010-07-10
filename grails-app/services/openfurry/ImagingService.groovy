package openfurry

import java.awt.image.*
import java.awt.geom.*
import javax.imageio.ImageIO
import javax.imageio.stream.ImageInputStream

class ImagingService {

    static transactional = false

    def createImageUserObjectFiles(Person user, multiPartFile, File path, Long time) {
        BufferedImage original = ImageIO.read(multiPartFile.inputStream)

        def full = new File(path, "${time}.${user.username}_${multiPartFile.getOriginalFilename()}")
        def sized = new File(path, "sized.${time}.${user.username}_${multiPartFile.getOriginalFilename()}")
        def thumb = new File(path, "thumb.${time}.${user.username}_${multiPartFile.getOriginalFilename()}")

        String format = ImageIO.getImageReadersByMIMEType(multiPartFile.getContentType()).getAt(0).getFormatName()

        ImageIO.write(resizeImage(original, 1280), format, full)
        ImageIO.write(resizeImage(original, 400), format, sized)
        ImageIO.write(resizeImage(original, 100), format, thumb)

        [ full, sized, thumb ]
    }

    def createThumbnailFile(multiPartFile, File dest) {
        BufferedImage original = ImageIO.read(multiPartFile.inputStream)

        if (original.width > 100 || original.height > 100) {
            original = resizeImage(original, 100)
        }

        String format = ImageIO.getImageReadersByMIMEType(multiPartFile.getContentType()).getAt(0).getFormatName()
        ImageIO.write(original, format, dest)

        dest
    }

    private static BufferedImage resizeImage(originalImage, maxDimension) {
        Double widthScale = (double)maxDimension / (double)originalImage.width
        Double heightScale = (double)maxDimension / (double)originalImage.height

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
