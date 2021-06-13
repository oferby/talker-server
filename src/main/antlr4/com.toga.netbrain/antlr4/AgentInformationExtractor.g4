grammar AgentInformationExtractor;

@header  {
    import com.toga.netbrain.model.db.entities.*;
    import com.toga.netbrain.model.db.entities.hw.*;
    import com.toga.netbrain.model.db.entities.hw.inf.*;
    import com.toga.netbrain.model.db.entities.management.*;
    import com.toga.netbrain.model.db.entities.org.*;
    import com.toga.netbrain.model.db.entities.sw.*;
    import com.toga.netbrain.model.db.entities.sw.network.*;

}

@members  {

}

/*
* Parser Rules
*/

extractor  returns [Element rootElement]:
    (
          hostElement { $rootElement = $hostElement.rootElement; }
        | agentElement { $rootElement = $agentElement.rootElement; }

    );

hostElement returns [HostAgent rootElement]:
    HOST hid=ID
    {
        $rootElement = new HostAgent($hid.text);
    };

agentElement returns [HostAgent rootElement]:
    HOST hid=ID AGENT aid=ID
    {
        $rootElement = new HostAgent($hid.text);
        $rootElement.addDeviceAgent(new DeviceAgent($aid.text));

    };


/*
* Lexer Rules
*/

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
fragment DIGIT      : [0-9]+ ;

NAME            : 'name';

CHASSIS         : 'chassis';
HOST            : 'host';
AGENT           : 'agent';
LINECARD        : 'linecard';
INTERFACE       : 'inf';
ETHERNET        : 'ethernet';
CPU             : 'cpu';
MEMORY          : 'memory';
PROCESS         : 'process';
MAINBOARD       : 'mainboard';
OS              : 'os';
NOS             : 'nos';
OP_STATUS       : 'op_status';
ADMIN_STATUS    : 'admin_status';
VERSION         : 'version';
VENDOR          : 'vendor';


VER_NUMBER      : (DIGIT | '.')+;
ID          : ( LOWERCASE | UPPERCASE | DIGIT | '_' | '-')+;

BACKSLASH   : [\\] -> skip ;