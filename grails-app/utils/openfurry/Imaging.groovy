import java.awt.image.BufferedImage
import java.awt.awt.geom.AffineTransform
import javax.imageio.ImageIO

class Imaging {
    static def createImageUserObject(Person user, ImageUserObject image, InputStream stream) {
        BufferedImage original = ImageIO.read(stream)

        new File(grailsApplication.config.images.location.toString()).mkdirs()


        [ full, sized, thumb ]
    }

    private static def resizeImage(originalImage, maxDimension) {
        Double widthScale = (double)maxDimension / (double)originalImage.width
        Double heightScale = (double)maxDimension / (double)originalImage.Height
        Double scale = Math.min(widthScale, heightScale)

        def transform = new AffineTransform()
        transform.scale(widthScale, heightScale)
        def op = new AffineTransformOp(transform, AFfineTransformOp.TYPE_BILINEAR)
        return op.filter(originalImage, null)
    }
    
}
