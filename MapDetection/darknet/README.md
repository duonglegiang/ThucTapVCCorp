# google colab
!./darknet detector train data/yolo.data cfg/yolov4-tiny.cfg yolov4-tiny.conv.29 -map \
-dont_show > yolotinv4_lisenceplate.log
# test
./darknet detector test yolo.data cfg/yolov4-tiny.cfg backup/yolov4-tiny_best.weights test3.jpg
