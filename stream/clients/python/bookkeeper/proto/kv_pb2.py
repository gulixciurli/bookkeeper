# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: kv.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='kv.proto',
  package='bookkeeper.proto.kv',
  syntax='proto3',
  serialized_options=_b('\n%org.apache.bookkeeper.stream.proto.kvP\001'),
  serialized_pb=_b('\n\x08kv.proto\x12\x13\x62ookkeeper.proto.kv\"\x8f\x01\n\x08KeyValue\x12\x0b\n\x03key\x18\x01 \x01(\x0c\x12\x17\n\x0f\x63reate_revision\x18\x02 \x01(\x03\x12\x14\n\x0cmod_revision\x18\x03 \x01(\x03\x12\x0f\n\x07version\x18\x04 \x01(\x03\x12\r\n\x05value\x18\x05 \x01(\x0c\x12\x11\n\tis_number\x18\x06 \x01(\x08\x12\x14\n\x0cnumber_value\x18\x07 \x01(\x03\"\xb8\x01\n\x05\x45vent\x12\x32\n\x04type\x18\x01 \x01(\x0e\x32$.bookkeeper.proto.kv.Event.EventType\x12)\n\x02kv\x18\x02 \x01(\x0b\x32\x1d.bookkeeper.proto.kv.KeyValue\x12.\n\x07prev_kv\x18\x03 \x01(\x0b\x32\x1d.bookkeeper.proto.kv.KeyValue\" \n\tEventType\x12\x07\n\x03PUT\x10\x00\x12\n\n\x06\x44\x45LETE\x10\x01\x42)\n%org.apache.bookkeeper.stream.proto.kvP\x01\x62\x06proto3')
)



_EVENT_EVENTTYPE = _descriptor.EnumDescriptor(
  name='EventType',
  full_name='bookkeeper.proto.kv.Event.EventType',
  filename=None,
  file=DESCRIPTOR,
  values=[
    _descriptor.EnumValueDescriptor(
      name='PUT', index=0, number=0,
      serialized_options=None,
      type=None),
    _descriptor.EnumValueDescriptor(
      name='DELETE', index=1, number=1,
      serialized_options=None,
      type=None),
  ],
  containing_type=None,
  serialized_options=None,
  serialized_start=332,
  serialized_end=364,
)
_sym_db.RegisterEnumDescriptor(_EVENT_EVENTTYPE)


_KEYVALUE = _descriptor.Descriptor(
  name='KeyValue',
  full_name='bookkeeper.proto.kv.KeyValue',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='key', full_name='bookkeeper.proto.kv.KeyValue.key', index=0,
      number=1, type=12, cpp_type=9, label=1,
      has_default_value=False, default_value=_b(""),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='create_revision', full_name='bookkeeper.proto.kv.KeyValue.create_revision', index=1,
      number=2, type=3, cpp_type=2, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='mod_revision', full_name='bookkeeper.proto.kv.KeyValue.mod_revision', index=2,
      number=3, type=3, cpp_type=2, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='version', full_name='bookkeeper.proto.kv.KeyValue.version', index=3,
      number=4, type=3, cpp_type=2, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='value', full_name='bookkeeper.proto.kv.KeyValue.value', index=4,
      number=5, type=12, cpp_type=9, label=1,
      has_default_value=False, default_value=_b(""),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='is_number', full_name='bookkeeper.proto.kv.KeyValue.is_number', index=5,
      number=6, type=8, cpp_type=7, label=1,
      has_default_value=False, default_value=False,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='number_value', full_name='bookkeeper.proto.kv.KeyValue.number_value', index=6,
      number=7, type=3, cpp_type=2, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=34,
  serialized_end=177,
)


_EVENT = _descriptor.Descriptor(
  name='Event',
  full_name='bookkeeper.proto.kv.Event',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='type', full_name='bookkeeper.proto.kv.Event.type', index=0,
      number=1, type=14, cpp_type=8, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='kv', full_name='bookkeeper.proto.kv.Event.kv', index=1,
      number=2, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='prev_kv', full_name='bookkeeper.proto.kv.Event.prev_kv', index=2,
      number=3, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
    _EVENT_EVENTTYPE,
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=180,
  serialized_end=364,
)

_EVENT.fields_by_name['type'].enum_type = _EVENT_EVENTTYPE
_EVENT.fields_by_name['kv'].message_type = _KEYVALUE
_EVENT.fields_by_name['prev_kv'].message_type = _KEYVALUE
_EVENT_EVENTTYPE.containing_type = _EVENT
DESCRIPTOR.message_types_by_name['KeyValue'] = _KEYVALUE
DESCRIPTOR.message_types_by_name['Event'] = _EVENT
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

KeyValue = _reflection.GeneratedProtocolMessageType('KeyValue', (_message.Message,), dict(
  DESCRIPTOR = _KEYVALUE,
  __module__ = 'kv_pb2'
  # @@protoc_insertion_point(class_scope:bookkeeper.proto.kv.KeyValue)
  ))
_sym_db.RegisterMessage(KeyValue)

Event = _reflection.GeneratedProtocolMessageType('Event', (_message.Message,), dict(
  DESCRIPTOR = _EVENT,
  __module__ = 'kv_pb2'
  # @@protoc_insertion_point(class_scope:bookkeeper.proto.kv.Event)
  ))
_sym_db.RegisterMessage(Event)


DESCRIPTOR._options = None
# @@protoc_insertion_point(module_scope)
