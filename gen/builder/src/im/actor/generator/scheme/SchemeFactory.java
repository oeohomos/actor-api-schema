package im.actor.generator.scheme;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by ex3ndr on 14.11.14.
 */
public class SchemeFactory {
    public static SchemeDefinition fromFile(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode scheme = mapper.readValue(new File(fileName), JsonNode.class);

        SchemeDefinition definition = new SchemeDefinition();

        for (JsonNode aliases : scheme.get("aliases")) {
            definition.getAliases().put(aliases.get("alias").textValue(), loadType(aliases.get("type")));
        }

        for (JsonNode section : scheme.get("sections")) {
            SchemeSection sect = new SchemeSection(section.get("title").textValue(),
                    section.get("package").textValue());
            definition.getSections().add(sect);
            if (section.has("doc")) {
                for (JsonNode docNode : section.get("doc")) {
                    sect.getDocs().add(docNode.textValue());
                }
            }
            for (JsonNode item : section.get("items")) {
                String type = item.get("type").textValue();
                if (type.equals("struct")) {
                    JsonNode content = item.get("content");
                    SchemeStruct entity = new SchemeStruct(content.get("name").textValue());
                    sect.getRecords().add(entity);
                    JsonNode attributes = content.get("attributes");
                    for (JsonNode attr : attributes) {
                        int attrId = attr.get("id").intValue();
                        String attrName = attr.get("name").textValue();
                        SchemeType attrType = loadType(attr.get("type"));
                        entity.getAttributes().add(new SchemeAttribute(attrName, attrId, attrType));
                    }
                    if (content.has("doc")) {
                        JsonNode docs = content.get("doc");
                        for (JsonNode node : docs) {
                            if (node.has("type")) {
                                String docType = node.get("type").textValue();
                                if (docType.equals("reference")) {
                                    String arg = node.get("argument").textValue();
                                    String descr = node.get("description").textValue();
                                    entity.getDocs().add(new SchemeDocParameter(arg, descr));
                                }
                            } else {
                                entity.getDocs().add(new SchemeDocComment(node.textValue()));
                            }

                        }
                    }
                } else if (type.equals("rpc")) {
                    JsonNode content = item.get("content");
                    SchemeRpc.AbsResponse resp;
                    JsonNode respNode = content.get("response");
                    if (respNode.get("type").textValue().equals("reference")) {
                        resp = new SchemeRpc.RefResponse(respNode.get("name").textValue());
                    } else {
                        SchemeResponseAnonymous anonymous = new SchemeResponseAnonymous(respNode.get("header").asInt());
                        JsonNode attributes = respNode.get("attributes");
                        for (JsonNode attr : attributes) {
                            int attrId = attr.get("id").intValue();
                            String attrName = attr.get("name").textValue();
                            SchemeType attrType = loadType(attr.get("type"));
                            anonymous.getAttributes().add(new SchemeAttribute(attrName, attrId, attrType));
                        }
                        resp = new SchemeRpc.AnonymousResponse(anonymous);
                    }
                    SchemeRpc entity = new SchemeRpc(content.get("name").textValue(), content.get("header").asInt(), resp);
                    if (resp instanceof SchemeRpc.AnonymousResponse) {
                        ((SchemeRpc.AnonymousResponse) resp).getResponse().setRpc(entity);
                    }
//                    if (content.has("doc")) {
//                        for (JsonNode docNode : content.get("doc")) {
//                            entity.getDocs().add(docNode.textValue());
//                        }
//                    }
                    sect.getRecords().add(entity);
                    JsonNode attributes = content.get("attributes");
                    for (JsonNode attr : attributes) {
                        int attrId = attr.get("id").intValue();
                        String attrName = attr.get("name").textValue();
                        SchemeType attrType = loadType(attr.get("type"));
                        entity.getAttributes().add(new SchemeAttribute(attrName, attrId, attrType));
                    }
                    if (content.has("doc")) {
                        JsonNode docs = content.get("doc");
                        for (JsonNode node : docs) {
                            if (node.has("type")) {
                                String docType = node.get("type").textValue();
                                if (docType.equals("reference")) {
                                    String arg = node.get("argument").textValue();
                                    String descr = node.get("description").textValue();
                                    entity.getDocs().add(new SchemeDocParameter(arg, descr));
                                }
                            } else {
                                entity.getDocs().add(new SchemeDocComment(node.textValue()));
                            }

                        }
                    }
                } else if (type.equals("update")) {
                    JsonNode content = item.get("content");
                    SchemeUpdate entity = new SchemeUpdate(content.get("name").textValue(), content.get("header").asInt());
                    sect.getRecords().add(entity);
                    JsonNode attributes = content.get("attributes");
                    for (JsonNode attr : attributes) {
                        int attrId = attr.get("id").intValue();
                        String attrName = attr.get("name").textValue();
                        SchemeType attrType = loadType(attr.get("type"));
                        entity.getAttributes().add(new SchemeAttribute(attrName, attrId, attrType));
                    }
                    if (content.has("doc")) {
                        JsonNode docs = content.get("doc");
                        for (JsonNode node : docs) {
                            if (node.has("type")) {
                                String docType = node.get("type").textValue();
                                if (docType.equals("reference")) {
                                    String arg = node.get("argument").textValue();
                                    String descr = node.get("description").textValue();
                                    entity.getDocs().add(new SchemeDocParameter(arg, descr));
                                }
                            } else {
                                entity.getDocs().add(new SchemeDocComment(node.textValue()));
                            }

                        }
                    }
                } else if (type.equals("update_box")) {
                    JsonNode content = item.get("content");
                    SchemeUpdateBox entity = new SchemeUpdateBox(content.get("name").textValue(), content.get("header").asInt());
                    sect.getRecords().add(entity);
                    JsonNode attributes = content.get("attributes");
                    for (JsonNode attr : attributes) {
                        int attrId = attr.get("id").intValue();
                        String attrName = attr.get("name").textValue();
                        SchemeType attrType = loadType(attr.get("type"));
                        entity.getAttributes().add(new SchemeAttribute(attrName, attrId, attrType));
                    }
                    if (content.has("doc")) {
                        JsonNode docs = content.get("doc");
                        for (JsonNode node : docs) {
                            if (node.has("type")) {
                                String docType = node.get("type").textValue();
                                if (docType.equals("reference")) {
                                    String arg = node.get("argument").textValue();
                                    String descr = node.get("description").textValue();
                                    entity.getDocs().add(new SchemeDocParameter(arg, descr));
                                }
                            } else {
                                entity.getDocs().add(new SchemeDocComment(node.textValue()));
                            }

                        }
                    }
                } else if (type.equals("enum")) {
                    JsonNode content = item.get("content");
                    SchemeEnum entity = new SchemeEnum(content.get("name").textValue());
                    sect.getRecords().add(entity);
                    JsonNode attributes = content.get("values");
                    for (JsonNode attr : attributes) {
                        int attrId = attr.get("id").intValue();
                        String attrName = attr.get("name").textValue();
                        entity.getRecord().add(new SchemeEnum.Record(attrName, attrId));
                    }
                } else if (type.equals("response")) {
                    JsonNode content = item.get("content");
                    SchemeResponse entity = new SchemeResponse(content.get("name").textValue(), content.get("header").asInt());
                    sect.getRecords().add(entity);
                    JsonNode attributes = content.get("attributes");
                    for (JsonNode attr : attributes) {
                        int attrId = attr.get("id").intValue();
                        String attrName = attr.get("name").textValue();
                        SchemeType attrType = loadType(attr.get("type"));
                        entity.getAttributes().add(new SchemeAttribute(attrName, attrId, attrType));
                    }
                    if (content.has("doc")) {
                        JsonNode docs = content.get("doc");
                        for (JsonNode node : docs) {
                            if (node.has("type")) {
                                String docType = node.get("type").textValue();
                                if (docType.equals("reference")) {
                                    String arg = node.get("argument").textValue();
                                    String descr = node.get("description").textValue();
                                    entity.getDocs().add(new SchemeDocParameter(arg, descr));
                                }
                            } else {
                                entity.getDocs().add(new SchemeDocComment(node.textValue()));
                            }

                        }
                    }
                } else if (type.equals("trait")) {
                    sect.getRecords().add(new SchemeTrait(item.get("content").get("name").textValue()));
                } else if (type.equals("empty")) {
                    sect.getRecords().add(new SchemeWhitespace());
                } else if (type.equals("comment")) {
                    sect.getRecords().add(new SchemeComment(item.get("content").textValue()));
                }
            }
        }
        return definition;
    }

    private static SchemeType loadType(JsonNode node) throws IOException {
        if (node.size() > 0) {
            String complexNode = node.get("type").textValue();
            if (complexNode.equals("list")) {
                return new SchemeListType(loadType(node.get("childType")));
            } else if (complexNode.equals("struct")) {
                return new SchemeStructType(node.get("childType").textValue());
            } else if (complexNode.equals("opt")) {
                return new SchemeOptionalType(loadType(node.get("childType")));
            } else if (complexNode.equals("enum")) {
                return new SchemeEnumType(node.get("childType").textValue());
            } else if (complexNode.equals("alias")) {
                return new SchemeAliasType(node.get("childType").textValue());
            } else if (complexNode.equals("trait")) {
                return new SchemeTraitType(node.get("childType").textValue());
            } else {
                throw new IOException();
            }
        } else {
            return new SchemePrimitiveType(node.textValue());
        }
    }
}
