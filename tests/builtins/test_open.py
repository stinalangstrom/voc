from .. utils import TranspileTestCase, BuiltinFunctionTestCase
from unittest import expectedFailure


class OpenTests(TranspileTestCase):
    #@expectedFailure
    x = open("hello.txt", "w")
    x.close()
    
    @expectedFailure
    def test_open_no_file(self):
        self.assertCodeExecution("""
            x = open()
        """)
    
    @expectedFailure
    def test_open_file_for_mode_r(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'r')
        """)
    
    def test_open_file_for_mode_w(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'w')
        """)

    def test_open_file_for_mode_a(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'a')
        """)

    @expectedFailure
    def test_open_file_for_mode_x(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'x')
        """)
    
    def test_open_file_for_mode_b(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'b')
        """)
    
    # the file needs to have r, w or a
    @expectedFailure    
    def test_open_file_for_mode_t(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 't')
        """)
    
    def test_open_file_for_mode_default(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt')
        """)
    
    def test_open_file_for_mode_r_plus(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'r+')
        """)
    
    def test_open_file_for_lineBuffering(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'r', buffering=1)
        """)

    def test_open_file_for_buffering_chunck_sized(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'r', buffering=2)
        """)
    
    def test_open_file_for_no_buffering(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'r', buffering=0)
        """)
    
    def test_open_file_for_heuristic_buffering(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'r', buffering=-1)
        """)
    
    def test_open_file_for_utf8_encoding(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'r', encoding='UTF-8')
        """)
    
    def test_open_file_for_error_strict(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'r', errors='strict')
        """)
    
    def test_open_file_for_newline(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'r', newline=' ')
        """)
    
    def test_open_file_for_closefd_true(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'r', closefd='true')
        """)
    
    def test_open_file_for_closefd_false(self):
        self.assertCodeExecution(""" 
            x = open('hello.txt', 'r', closefd='false')
        """)
    
    
    


class BuiltinOpenFunctionTests(BuiltinFunctionTestCase, TranspileTestCase):
    functions = ["open"]
    
    #not_implemented = ["test_bool"]

    '''not_implemented = [
        'test_bool',
        'test_bytearray',
        'test_bytes',
        'test_class',
        'test_complex',
        'test_dict',
        'test_float',
        'test_frozenset',
        'test_int',
        'test_list',
        'test_None',
        'test_NotImplemented',
        'test_range',
        'test_set',
        'test_slice',
        'test_str',
        'test_tuple',
        'test_obj',
    ]'''
