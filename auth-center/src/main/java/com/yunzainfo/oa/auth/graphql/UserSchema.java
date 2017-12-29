package com.yunzainfo.oa.auth.graphql;

import com.yunzainfo.oa.auth.repository.BaseUserRepository;
import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/29
 * Email:old_camel@126.com
 */
@Service
public class UserSchema {
    private static GraphQLOutputType userType;
    private static GraphQLOutputType roleType;
    private static GraphQLOutputType authorityType;
    private static GraphQLOutputType deptType;
    public static GraphQLSchema schema;
    @Autowired
    private BaseUserRepository repository;

    private void initUserType() {
        userType = newObject().name("user")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("id").type(Scalars.GraphQLID).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("username").type(Scalars.GraphQLString).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("email").type(Scalars.GraphQLString).build())
                .build();
    }

    private GraphQLFieldDefinition initUserField() {
        return GraphQLFieldDefinition.newFieldDefinition().name("user")
                .argument(newArgument().name("id").type(Scalars.GraphQLID).build()).type(userType).dataFetcher(environment -> {
                    String id = environment.getArgument("id");
                    return repository.findOne(id);
                }).build();
    }

    public GraphQLSchema  GraphSchema() {
        initUserType();
        schema= GraphQLSchema.newSchema().query(newObject()
                .name("GraphQuery")
                .field(initUserField())
                .build()).build();
return schema;
    }
}
