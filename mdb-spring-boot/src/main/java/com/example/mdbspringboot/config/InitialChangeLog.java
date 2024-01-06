package com.example.mdbspringboot.config;



import com.example.mdbspringboot.model.GroceryItem;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

//@ChangeUnit(id = "222", order = "001")
@ChangeLog
public class InitialChangeLog {

    @ChangeSet(order = "001", author = "john", id = "ddd")
    public void createUserCollection(MongoTemplate mongoTemplate) {
      //  mongoTemplate.dropCollection("users");
        mongoTemplate.createCollection("users");
    }
/*

    @ChangeSet(order = "005", author = "maher", id = "005")
    public void createUser2Collection(MongoTemplate mongoTemplate) {

        String indexCommand = "{ createIndexes: 'GroceryItem', indexes: [ { key: { yourField: 1 }, name: 'yourIndex' } ] }";
        // Execute the createIndexes command
        String result = String.valueOf(mongoTemplate.executeCommand(indexCommand));
        // Process the result
        System.out.println("Create Index Result: " + result);
    }
*/

    @ChangeSet(order = "013", author = "maher", id = "013")
    public void createanotherIndex(MongoTemplate mongoTemplate) {

        String indexCommand = "{ createIndexes: 'GroceryItem', indexes: [ { key: { name: -1, category: 1  }, name: 'newname' } ] }";
        // Execute the createIndexes command
        String result = String.valueOf(mongoTemplate.executeCommand(indexCommand));
        // Process the result
        System.out.println("Create Index Result: " + result);
    }


   /*     Document updateCommand = new Document("update", "GroceryItem")
                .append("update", new Document("_id", "Dried Red Chilli"))
                .append("update", new Document("$set", new Document("newField", "newValue")));
        mongoTemplate.executeCommand(updateCommand);*/



    @ChangeSet(order = "006", author = "maher", id = "006")
    public void addNewFieldToDocument(MongoTemplate mongoTemplate){
        // Specify the condition to identify the document(s) you want to update
        Criteria criteria = Criteria.where("name").is("Dried Whole Red Chilli");

        // Build the query
        Query query = new Query(criteria);

        // Build the update operation to add the new field
        Update update = new Update().set("newField", "yourNewValue");

        // Update the first matching document
        mongoTemplate.updateFirst(query, update, GroceryItem.class);

        // If you want to update all matching documents, use updateMulti instead
        // mongoTemplate.updateMulti(query, update, GroceryItem.class);
    }

    @ChangeSet(order = "007", author = "maher", id = "007")
    public void addNewFieldToAllDocuments(MongoTemplate mongoTemplate) {
        // Build the update operation to add the new field
        Update update = new Update().set("new2", "yourNewValue");

        Query query = new Query();
        // Update all documents in the collection
        mongoTemplate.updateMulti(query, update, GroceryItem.class);
    }


}
