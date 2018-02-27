# messaging

Message processing application.

As an input application accepts a file with below structure:

message_type|product_name|price|sales_count|operation_type

 - message_type possible values: 1,2,3;
 - product_name: any string;
 - price: string in format xxxxxx.xx;
 - sales_count: any integer (for message_type = (1 and 3) sales_count = 1);
 - operation_type possible values: "A", "M", "S", "N" 
   (for message_type = (1 and 2) always operation_type = N; 
    for message_type = 3 operation_type any of "A" "M" "S");

How to run:
 - build project;
 - run from messaging/target ;

java -cp ./messaging-0.0.1-SNAPSHOT.jar com.app.processing.processor.MessageProcessor ../resources/data.txt


Notes:

 - application partly covered by tests for demo purposes only;