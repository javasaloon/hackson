import leancloud

leancloud.init("k9Pgs0dnjsA6gs039NAEPvY0-gzGzoHsz", "BLYbwE8UInNaEVGxKpYhLk5k")

import logging

logging.basicConfig(level=logging.DEBUG)

TestObject = leancloud.Object.extend('TestObject')
test_object = TestObject()
test_object.set('words', "Hello World!")
test_object.save()
