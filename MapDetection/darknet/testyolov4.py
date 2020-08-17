import cv2
import matplotlib.pyplot as plt

path = "predictions.jpg"
image = cv2.imread(path)
original_width, original_height = image.shape[1], image.shape[0]
resized_image = cv2.resize(image, (2*original_width, 2*original_height), interpolation = cv2.INTER_CUBIC)
resized_image = cv2.cvtColor(resized_image, cv2.COLOR_BGR2RGB)
plt.figure(figsize=(20,10))
plt.axis("off")
plt.imshow(resized_image)
plt.show()