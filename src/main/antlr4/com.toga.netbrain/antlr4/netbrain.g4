grammar netbrain;

@header {
    import java.util.*;

}

@members {
    HashMap<String,String> values;
}

/*
* Parser Rules
*/

bn  returns [Map<String,String> values] :
        ( addNewAgentHost { $values = $addNewAgentHost.values; }
        | addNewAgent { $values = $addNewAgent.values; }
        );

addNewAgentHost returns [Map<String,String> values]
    : ADD NEW? HOST h=(CHARS | CHARS_AND_DIGITS)
        {
            $values = new HashMap<String,String>();
            $values.put("operator", "addNewAgentHost");
            $values.put("host", $h.text);
        };

addNewAgent returns [Map<String,String> values]
    : ADD NEW? AGENT h=(CHARS | CHARS_AND_DIGITS) USERNAME u=(CHARS | CHARS_AND_DIGITS) PASSWORD p=(CHARS | CHARS_AND_DIGITS)
        {
            $values = new HashMap<String,String>();
            $values.put("operator", "addNewAgent");
            $values.put("host", $h.text);
            $values.put("username", $u.text);
            $values.put("password", $p.text);
        };



/*
* Lexer Rules
*/

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
fragment DIGIT      : [0-9]+ ;


AND         : 'and' ;
ACCESS      : 'access';
ALLOW       : ( 'allow' | 'grant' | 'permit' ) ;
ADD         : 'add';
ADDRESS     : 'address';
AGENT       : 'agent' 's'?;
ALL         : 'all';

CONNECT     : ( 'connect' | 'attach' ) ;
CREATE      : ( 'create' | 'build' | 'start' ) ;
CALCULATE   : ('calculate' | 'calc' );
CLEAR       : 'clear' ;
CONFIG      : ( 'config' | 'configure' );

DISCONNECT  : ( 'disconnect' | 'detach' ) ;
DELETE      : ( 'delete' | 'remove' ) ;
DENY        : ( 'deny' | 'revoke' ) ;
DEMO        : 'demo' ;

ENTITY      : ('router' | 'vm' | 'virtual machine' | 'ecs' | 'server'| 'switch' |  'firewall' | 'service' | 'application' | 'mpls switch') 's'? ;

FROM        : 'from' ;
FOR         : 'for' ;

GROUP       : 'group' 's'? ;

HELP        : 'help';
HOST        : 'host';

IP          : 'ip';
IPADDRESS   : [1-2]? [0-9]? [0-5] '.' [1-2]? [0-9]? [0-5] '.' [1-2]? [0-9]? [0-5] '.' [1-2]? [0-9]? [0-5];

LENGTH      : 'length';
LIMIT       : 'limit';

MPLS        : 'mpls';
MAX         : 'max';

NUMBER      : DIGIT;
NEW         : 'new';

ON          : 'on';

POLICY      : ( 'policy' | 'policies' ) ;
PROTOCOL    : ('mpls' | 'ethernet' ) ;
PASSWORD    : 'password';

SHORTEST       : ( 'shortest' | 'short' );
SHOW        : ( 'show' |  'get' ) ;
SEARCH      : ( 'find' | 'search' ) ;
SEARCHABLE  : ( 'path' | 'traffic' ) ;
SETUP       : 'setup';

TRAFFIC     : 'traffic';
TO          : 'to' ;

USERNAME    : 'username' ;

WITH        : 'with';

NEWLINE             : ('\r'? '\n' | '\r')+ ;
CHARS               : ( LOWERCASE | UPPERCASE | '_' )+ ;
CHARS_AND_DIGITS    : ( LOWERCASE | UPPERCASE | DIGIT | '_' | '-')+ ;
WHITESPACE          : ' ' -> skip ;
